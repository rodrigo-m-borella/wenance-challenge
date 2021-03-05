package com.wenance.challenge.argbitcoin.domain.mapper;

import com.wenance.challenge.argbitcoin.domain.dto.BuenbitObjectResponseDTO;
import com.wenance.challenge.argbitcoin.domain.dto.BuenbitResponseDTO;
import com.wenance.challenge.argbitcoin.model.documet.BtcDaiDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface SourceToDestinationMapper {
	@Mappings({
			@Mapping(target = "daiars.currency", source = "buenbitResponseDTO.object.daiars.currency"),
			@Mapping(target = "daiars.bidCurrency", source = "buenbitResponseDTO.object.daiars.bidCurrency"),
			@Mapping(target = "daiars.purchasePrice", source = "buenbitResponseDTO.object.daiars.purchasePrice"),
			@Mapping(target = "daiars.sellingPrice", source = "buenbitResponseDTO.object.daiars.sellingPrice"),
			@Mapping(target = "daiars.marketIdentifier", source = "buenbitResponseDTO.object.daiars.marketIdentifier"),
			@Mapping(target = "daiars.askCurrency", source = "buenbitResponseDTO.object.daiars.askCurrency"),

			@Mapping(target = "daiusd.currency", source = "buenbitResponseDTO.object.daiusd.currency"),
			@Mapping(target = "daiusd.bidCurrency", source = "buenbitResponseDTO.object.daiusd.bidCurrency"),
			@Mapping(target = "daiusd.purchasePrice", source = "buenbitResponseDTO.object.daiusd.purchasePrice"),
			@Mapping(target = "daiusd.sellingPrice", source = "buenbitResponseDTO.object.daiusd.sellingPrice"),
			@Mapping(target = "daiusd.marketIdentifier", source = "buenbitResponseDTO.object.daiusd.marketIdentifier"),
			@Mapping(target = "daiusd.askCurrency", source = "buenbitResponseDTO.object.daiusd.askCurrency"),

			@Mapping(target = "btcars.currency", source = "buenbitResponseDTO.object.btcars.currency"),
			@Mapping(target = "btcars.bidCurrency", source = "buenbitResponseDTO.object.btcars.bidCurrency"),
			@Mapping(target = "btcars.purchasePrice", source = "buenbitResponseDTO.object.btcars.purchasePrice"),
			@Mapping(target = "btcars.sellingPrice", source = "buenbitResponseDTO.object.btcars.sellingPrice"),
			@Mapping(target = "btcars.marketIdentifier", source = "buenbitResponseDTO.object.btcars.marketIdentifier"),
			@Mapping(target = "btcars.askCurrency", source = "buenbitResponseDTO.object.btcars.askCurrency"),
	})
	public BtcDaiDocument buenbitResponseDTOToBtcDaiDocument(BuenbitResponseDTO buenbitResponseDTO);

	@Mappings(
			@Mapping(source = "date", target = "date", qualifiedByName = "toArgZonedInstant")
	)
	public BuenbitObjectResponseDTO btcDaiDocumentDTOToBuenbitObjectResponseDTO(BtcDaiDocument btcDaiDocument);

	@Named("toArgZonedInstant")
	public static LocalDateTime getArgZonedInstant(Instant utcInstant) {
		return LocalDateTime.ofInstant(utcInstant, ZoneId.of("America/Argentina/Buenos_Aires"));
	}
}
