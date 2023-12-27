package com.fullcycle.admin.catalogo.application.category.retrive.list;

import com.fullcycle.admin.catalogo.application.UseCase;
import com.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoryUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
