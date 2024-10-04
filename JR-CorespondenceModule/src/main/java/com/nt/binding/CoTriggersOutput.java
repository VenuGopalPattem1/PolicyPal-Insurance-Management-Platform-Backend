package com.nt.binding;

import lombok.Data;

@Data
public class CoTriggersOutput {
	private Integer totalTriggers;
	private Integer pendingTriggers;
	private Integer successTriggers;
}
