package com.miho.spring5mvcrest.api.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("Vendor")
data class VendorDTO(
        @ApiModelProperty("Name", required = true)
        var name: String? = null,

        @ApiModelProperty("Vendor URL")
        var vendor_url: String? = null)