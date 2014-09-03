package com.destiny1020.hranalyzer.rest.response;

import java.util.List;

import org.springframework.data.domain.Page;

public class PagePackage<T> {

    private List<T> contents;
    private long totalCount;
    private int totalPages;

    public PagePackage(Page<T> pageObject) {
        this.contents = pageObject.getContent();
        this.totalCount = pageObject.getTotalElements();
        this.totalPages = pageObject.getTotalPages();
    }

    public List<T> getContents() {
        return contents;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

}
