package me.kjs.apimanagement.product.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectRestController {

	@PutMapping("/{productCode}")
	fun createProject(
		@RequestBody request: ProjectForm.Put.Request,
		@PathVariable productCode: ProductCode
	): ResponseEntity<ProjectForm.Put.Response> {
		TODO()
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun queryProjects(

	): ProjectForm.Find.Response.All {
		TODO()
	}

	@GetMapping("/{productCode}")
	@ResponseStatus(HttpStatus.OK)
	fun queryProject(
		@PathVariable productCode: ProductCode
	): ProjectForm.Find.Response.One {
		TODO()
	}


}