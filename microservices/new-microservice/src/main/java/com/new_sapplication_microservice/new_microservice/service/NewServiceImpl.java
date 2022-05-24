package com.new_sapplication_microservice.new_microservice.service;

import com.new_sapplication_microservice.new_microservice.dto.CategoryDTO;
import com.new_sapplication_microservice.new_microservice.dto.CreateANewDTO;
import com.new_sapplication_microservice.new_microservice.dto.NewDTO;
import com.new_sapplication_microservice.new_microservice.entity.Author;
import com.new_sapplication_microservice.new_microservice.entity.CategoryEntity;
import com.new_sapplication_microservice.new_microservice.entity.NewEntity;
import com.new_sapplication_microservice.new_microservice.enums.CategoryEnum;
import com.new_sapplication_microservice.new_microservice.exception.NewNotFoundException;
import com.new_sapplication_microservice.new_microservice.exception.NotFoundCategoryException;
import com.new_sapplication_microservice.new_microservice.repository.CategoryRepository;
import com.new_sapplication_microservice.new_microservice.repository.NewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewService {
    private final NewRepository newRepository;
    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<NewDTO> findAllNews() {
        List<NewEntity> news = newRepository.findAll();

        return news.stream().map(this::mapNewEntityToDto).collect(Collectors.toList());
    }

    @Override
    public NewDTO findById(String id) {
        NewEntity newEntity = newRepository.findById(id).orElseThrow(() -> new NewNotFoundException(id));
        return mapDtoToNewEntity(newEntity);
    }

    @Override
    public NewEntity createANew(CreateANewDTO newDto) {
        Author author = restTemplate.getForObject("http://localhost:8081/api/v1/users/get-first", Author.class);

        NewEntity newEntity = NewEntity.builder()
                .authorId(author.getId())
                .mangerId(null)
                .categories(newDto.getCategories().stream().map(this::getCategory).collect(Collectors.toList()))
                .createdDate(LocalDateTime.now())
                .editedDate(null)
                .description(newDto.getDescription())
                .isActive(false)
                .topic(newDto.getTopic())
                .description(newDto.getDescription())
                .views(0)
                .mainPicture(newDto.getMainPicture())
                .secondPicture(newDto.getSecondPicture())
                .thirdPicture(newDto.getThirdPicture())
                .forthPicture(newDto.getForthPicture())
                .build();

        return newRepository.save(newEntity);
    }

    @Override
    public List<NewDTO> findAllByCategory(String categoryId) {
        List<NewEntity> news = newRepository.findAllByCategoriesId(categoryId);

        return news.stream().map(aNew -> modelMapper.map(aNew, NewDTO.class)).collect(Collectors.toList());
    }

    private CategoryEntity getCategory(String categoryName) {

        return categoryRepository.findByName(CategoryEnum.valueOf(categoryName.toUpperCase()).getName())
                .orElseThrow(() -> new NotFoundCategoryException(categoryName));
    }

    private NewDTO mapDtoToNewEntity(NewEntity newEntity) {

        return NewDTO.builder()
                .authorId(newEntity.getAuthorId())
                .views(newEntity.getViews())
                .mangerId(newEntity.getMangerId())
                .categories(newEntity.getCategories().stream().map(this::mapCategoryEntityToDto).collect(Collectors.toList()))
                .mainPicture(newEntity.getMainPicture())
                .secondPicture(newEntity.getSecondPicture())
                .thirdPicture(newEntity.getThirdPicture())
                .forthPicture(newEntity.getForthPicture())
                .isActive(newEntity.getIsActive())
                .topic(newEntity.getTopic())
                .description(newEntity.getDescription())
                .id(newEntity.getId())
                .editedDate(newEntity.getEditedDate())
                .createdDate(newEntity.getCreatedDate())
                .build();
    }

    private NewDTO mapNewEntityToDto(NewEntity aNew) {

       return NewDTO.builder()
               .id(aNew.getId())
               .authorId(aNew.getAuthorId())
               .categories(mapCategoryEntitiesToCategoryDto(aNew))
               .createdDate(aNew.getCreatedDate())
               .editedDate(aNew.getEditedDate())
               .description(aNew.getDescription())
               .topic(aNew.getTopic())
               .isActive(aNew.getIsActive())
               .mainPicture(aNew.getMainPicture())
               .secondPicture(aNew.getSecondPicture())
               .thirdPicture(aNew.getThirdPicture())
               .forthPicture(aNew.getForthPicture())
               .mangerId(aNew.getMangerId())
               .authorId(aNew.getAuthorId())
               .views(aNew.getViews())
               .build();
    }

    private List<CategoryDTO> mapCategoryEntitiesToCategoryDto(NewEntity aNew) {
        return aNew.getCategories().stream().map(this::mapCategoryEntityToCategoryDto).collect(Collectors.toList());
    }

    private CategoryDTO mapCategoryEntityToCategoryDto(CategoryEntity a) {
        return modelMapper.map(a, CategoryDTO.class);
    }

    private CategoryDTO mapCategoryEntityToDto(CategoryEntity category) {

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
