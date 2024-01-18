package pl.mikolajp.forum.category;

class CategoryMapper {
    CategoryDao mapDtoToDao(CategoryDto categoryDto){
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.setCategoryName(categoryDto.getCategoryName());
        return categoryDao;
    }

    CategoryDao mapDtoToDao(CategoryDto categoryDto, Integer id){
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.setCategoryId(id);
        categoryDao.setCategoryName(categoryDto.getCategoryName());
        return categoryDao;
    }

    CategoryDto mapDaoToDto(CategoryDao categoryDao){
        return new CategoryDto(categoryDao.getCategoryId(), categoryDao.getCategoryName());
    }
}
