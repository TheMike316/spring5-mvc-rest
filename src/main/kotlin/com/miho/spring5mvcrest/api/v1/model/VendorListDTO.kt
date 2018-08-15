package com.miho.spring5mvcrest.api.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("VendorList")
data class VendorListDTO(
        @ApiModelProperty("Vendors")
        var vendors: List<VendorDTO>
)