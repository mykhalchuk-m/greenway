package com.mmm.grenway.javafx.controller.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;

public class DocumentsUtils {
	public static void calculateDocumentsStatus(DetailedOrderDto detailedOrderDto) {
		List<String> statuses = new ArrayList<>();
		if (detailedOrderDto.getInvitationDocument() != null) {
			statuses.add(detailedOrderDto.getInvitationDocument().getStatus().get().name());
		}
		if (detailedOrderDto.getDocumentPerOrder() != null && detailedOrderDto.getDocumentPerOrder().size() != 0) {
			statuses = detailedOrderDto.getDocumentPerOrder().stream().map(f -> f.getProcessingStatus().get().name())
					.collect(Collectors.toList());
		}

		if (statuses.contains(ProcessingStatus.IN_PROGRESS.name())) {
			detailedOrderDto.getDocumnentsStatus().set(ProcessingStatus.IN_PROGRESS);
		} else {
			statuses.sort((str1, str2) -> str1.compareTo(str2) * (-1));
			detailedOrderDto.getDocumnentsStatus().set(ProcessingStatus.valueOf(statuses.get(0)));
		}
	}
}
