package me.kjs.apimanagement.product.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectRestController {

	@PutMapping("/{projectCode}")
	fun createProject(
		@RequestBody request: ProjectForm.Put.Request,
		@PathVariable projectCode: ProjectCode
	): ResponseEntity<ProjectForm.Put.Response> {
		TODO()
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun queryProjects(

	): ProjectForm.Find.Response.All {
		TODO()
	}

	@GetMapping("/{projectCode}")
	@ResponseStatus(HttpStatus.OK)
	fun queryProject(
		@PathVariable projectCode: ProjectCode
	): ProjectForm.Find.Response.One {
		TODO()
	}


}