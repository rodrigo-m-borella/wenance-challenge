package com.wenance.challenge.argbitcoin.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuenbitResponseDTO {

	private BuenbitObjectResponseDTO object;
	private List<String> errors;



}
