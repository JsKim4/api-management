package me.kjs.apimanagement.applications.application.port.`in`

import me.kjs.apimanagement.applications.adapter.out.presenstation.ApplicationPresentation
import me.kjs.apimanagement.common.Response

interface FindOnPageApplicationUseCase {
	fun findSlice(page: Int, contentCount: Int): Response.Slice<ApplicationForm.Find.Response.Simple>
	fun findPage(page: Int, contentCount: Int): Response.Page<ApplicationForm.Find.Response.Simple>

}
