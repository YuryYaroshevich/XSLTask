<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">

	<xsl:output method="xml" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="color" />
	<xsl:param name="price" />

	<xsl:template match="node()">
		<xsl:copy>
			<xsl:apply-templates
				select="pr:products/pr:category[name=$categoryName]/pr:subcategory[name=$subcategoryName]=last()" />
		</xsl:copy>
	</xsl:template>

	<xsl:template
		match="pr:products/pr:category[name=$categoryName]/child::pr:subcategory[name=$subcategoryName]">
		<xsl:call-template name="saveGood">
			<xsl:with-param name="producer" select="$producer" />
			<xsl:with-param name="model" select="$model" />
			<xsl:with-param name="dateOfIssue" select="$dateOfIssue" />
			<xsl:with-param name="color" select="$color" />
			<xsl:with-param name="price" select="$price" />
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="saveGood">
		<xsl:param name="producer" />
		<xsl:param name="model" />
		<xsl:param name="dateOfIssue" />
		<xsl:param name="color" />
		<xsl:param name="price" />

		<xsl:template match="pr:producer">
			<xsl:element name=".">
				<xsl:value-of select="$producer" />
			</xsl:element>
		</xsl:template>
		<xsl:template match="pr:good/pr:model">
			<xsl:value-of select="$model" />
		</xsl:template>
		<xsl:template match="pr:good/pr:dateOfIssue">
			<xsl:value-of select="$dateOfIssue" />
		</xsl:template>
		<xsl:template match="pr:good/pr:color">
			<xsl:value-of select="$color" />
		</xsl:template>
		<xsl:template match="pr:good/pr:price">
			<xsl:value-of select="$price" />
		</xsl:template>
	</xsl:template>
</xsl:stylesheet>


<!-- <xsl:template name="saveGood"> <xsl:param name="producer" /> <xsl:param 
	name="model" /> <xsl:param name="dateOfIssue" /> <xsl:param name="color" 
	/> <xsl:param name="price" /> <pr:good> <pr:producer> <xsl:value-of select="$producer" 
	/> </pr:producer> <pr:model> <xsl:value-of select="$model" /> </pr:model> 
	<pr:dateOfIssue> <xsl:value-of select="$dateOfIssue" /> </pr:dateOfIssue> 
	<pr:color> <xsl:value-of select="$color" /> </pr:color> <xsl:choose> <xsl:when 
	test="$price != ''"> <xsl:value-of select="$price" /> </xsl:when> <xsl:otherwise> 
	<pr:notInStock /> </xsl:otherwise> </xsl:choose> </pr:good> </xsl:template> -->