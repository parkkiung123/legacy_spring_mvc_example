package com.example.dto;

public record Student(
    Integer id,
    String name,
    Integer classNum,
    String teacher,
    Integer korean,
    Integer english,
    Integer math,
    Integer science,
    Integer history
) {}