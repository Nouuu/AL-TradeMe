package org.larrieulacoste.noe.al.trademe.features.projects.web;

record UpdateProjectRequest(
		String taskName,
		Double dailyRate,
		String locationName,
		Double longitude, // Y
		Double latitude // X
) {
}
