package com.codefactory.petmanager.g12.petmanager_backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
  private final String message;
  private final int code;
}
