package com.bookservice.dto;

import com.bookservice.model.Book;

public record BookDTO (

        Book book,

        String currency,

        String environment

){
}
