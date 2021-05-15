package me.kjs.apimanagement.product.presentation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectRestController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createProject(
		@RequestBody request: ProjectForm.Create.Request
	): ProjectForm.Create.Response {
		TODO()
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun queryProjects(

	): ProjectForm.Find.Response.Page {
		TODO()
	}

	@GetMapping("/{projectId}")
	@ResponseStatus(HttpStatus.OK)
	fun queryProject(
		@PathVariable projectId: String
	): ProjectForm.Find.Response.One {
		TODO()
	}






}