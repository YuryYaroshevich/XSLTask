<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products"
	xmlns:validator="xalan://com.epam.xsl.util.GoodValidator">

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="color" />
	<xsl:param name="price" />
	<xsl:param name="notInStock" />

	<xsl:variable name="isValid" select="true" />

	<xsl:template match="/">
		<xsl:if test="1=1">
			<xsl:call-template name="saveGood" />
		</xsl:if>
	</xsl:template>

	<xsl:include href="saveGood.xslt" />
</xsl:stylesheet>