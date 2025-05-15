package nam.tran.home.assignment.jetpack.compose.fakes

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaseTest @Inject constructor(){
    var isCategorySuccess : Boolean = true
    var productType : Int = 0
    var isProductDetailSuccess : Boolean = true
}