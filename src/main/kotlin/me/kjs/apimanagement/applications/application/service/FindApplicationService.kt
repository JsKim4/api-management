package me.kjs.apimanagement.applications.application.service

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationForm
import me.kjs.apimanagement.applications.application.port.`in`.FindOnPageApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.GetOneApplicationUseCase
import me.kjs.apimanagement.common.Response
import org.springframework.stereotype.Service

@Service
class FindApplicationService:FindOnPageApplicationUseCase,GetOneApplicationUseCase {

	override fun findSlice(page: Int, contentCount: Int): Response.Slice<ApplicationForm.Find.Response.Simple> {
		TODO("Not yet implemented")
	}

	override fun findPage(page: Int, contentCount: Int): Response.Page<ApplicationForm.Find.Response.Simple> {
		TODO("Not yet implemented")
	}

	override fun getOne(applicationId: String): ApplicationForm.Find.Response.One {
		TODO("Not yet implemented")
	}
}