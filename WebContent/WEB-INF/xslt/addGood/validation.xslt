<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="color" />
	<xsl:param name="price" />

	<xsl:variable name="isValid" select="true" />

	<xsl:template match="/">
		<xsl:if test="1=1">
			<xsl:call-template name="saveGood">
				<xsl:with-param name="categoryName" select="$categoryName" />
				<xsl:with-param name="subcategoryName" select="$subcategoryName" />
				<xsl:with-param name="producer" select="$producer" />
				<xsl:with-param name="model" select="$model" />
				<xsl:with-param name="dateOfIssue" select="$dateOfIssue" />
				<xsl:with-param name="color" select="$color" />
				<xsl:with-param name="price" select="$price" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	
	<xsl:include href="saveGood.xslt" />
</xsl:stylesheet>