package nam.tran.fake

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaseTest @Inject constructor(){
    var isCategorySuccess : Boolean = true
    var productType : ProductType = ProductType.Success

    sealed class ProductType{
        object Success : ProductType()
        object SuccessButEmpty : ProductType()
        object LoadMoreError : ProductType()
        object Error : ProductType()
    }
}