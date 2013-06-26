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

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates
				select="pr:category[name=$categoryName]/pr:subcategory[name=$subcategoryName]|node()|@*" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="pr:subcategory[@name=$subcategoryName]">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
			<xsl:call-template name="saveGood">
				<xsl:with-param name="producer" select="$producer" />
				<xsl:with-param name="model" select="$model" />
				<xsl:with-param name="dateOfIssue" select="$dateOfIssue" />
				<xsl:with-param name="color" select="$color" />
				<xsl:with-param name="price" select="$price" />
			</xsl:call-template>
		</xsl:copy>
	</xsl:template>

	<xsl:template name="saveGood">
		<xsl:param name="producer" />
		<xsl:param name="model" />
		<xsl:param name="dateOfIssue" />
		<xsl:param name="color" />
		<xsl:param name="price" />

		<xsl:element name="pr:good">
			<xsl:element name="pr:producer">
				<xsl:value-of select="$producer" />
			</xsl:element>
			<xsl:element name="pr:model">
				<xsl:value-of select="$model" />
			</xsl:element>
			<xsl:element name="pr:date-of-issue">
				<xsl:value-of select="$dateOfIssue" />
			</xsl:element>
			<xsl:element name="pr:color">
				<xsl:value-of select="$color" />
			</xsl:element>
			<xsl:choose>
				<xsl:when test="$price">
					<xsl:element name="pr:price">
						<xsl:value-of select="$price" />
					</xsl:element>
				</xsl:when>
				<xsl:otherwise>
					<xsl:element name="pr:not-in-stock" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
