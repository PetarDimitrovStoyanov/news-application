package com.new_sapplication_microservice.new_microservice.service;

import com.new_sapplication_microservice.new_microservice.dto.CreateANewDTO;
import com.new_sapplication_microservice.new_microservice.dto.NewDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewService {
    private final NewRepository newRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<NewDTO> findAllByIsActiveTrueOrderByCreatedDateDesc() {
        List<NewEntity> news = newRepository.findAllByIsActiveTrueOrderByCreatedDateDesc();

        return news.stream().map(aNew -> modelMapper.map(aNew, NewDTO.class)).collect(Collectors.toList());
    }

    @Override
    public NewDTO findById(String id) {
        NewEntity newEntity = newRepository.findById(id).orElseThrow(() -> new NewNotFoundException(id));

        return modelMapper.map(newEntity, NewDTO.class);
    }

    @Override
    public NewEntity createANew(CreateANewDTO newDto) {

        NewEntity newEntity = NewEntity.builder()
                .authorId(newDto.getAuthorId())
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
        List<NewEntity> news = newRepository.findAllByCategoriesIdAndIsActiveTrue(categoryId);

        return news.stream().map(aNew -> modelMapper.map(aNew, NewDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<NewDTO> findAllManagement() {
        List<NewEntity> news = newRepository.findAll();

        return news.stream().map(aNew -> modelMapper.map(aNew, NewDTO.class)).collect(Collectors.toList());
    }

    private CategoryEntity getCategory(String categoryName) {

        return categoryRepository.findByName(CategoryEnum.valueOf(categoryName.toUpperCase()).getName())
                .orElseThrow(() -> new NotFoundCategoryException(categoryName));
    }
}
