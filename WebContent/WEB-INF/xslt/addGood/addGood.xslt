<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xalan="http://xml.apache.org/xslt"
	xmlns:good="xalan://com.epam.xsl.product.Good"
	xmlns:errors="xalan://java.util.Map"
	extension-element-prefixes="good errors">

	<xsl:output method="html" />
<!-- -->
	<xsl:template match="/">
		<xsl:param name="categoryName" />
		<xsl:param name="subcategoryName" />
		<xsl:param name="errors" />
		<xsl:param name="good" />
		<html>
			<head>
				<title>
					Add good
				</title>
			</head>
			<body>
				<h2>
					Add good in
					<xsl:value-of select="$subcategoryName" />
					subcategory
				</h2>
				<form action="controller">
					<input type="hidden" name="command" value="SAVE_GOOD" />
					<input type="hidden" name="categoryName" value="{$categoryName}" />
					<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
					<table>
						<tr>
							<td>Producer:</td>
							<td>
								<input type="text" name="producer" value="{good:getProducer($good)}" />
								<xsl:value-of select="errors:getValue($errors, 'producer')" />
							</td>
						</tr>
						<tr>
							<td>Model:</td>
							<td>
								<input type="text" name="model" value="{good:getModel($good)}" />
								<xsl:value-of select="errors:getValue($errors, 'model')" />
							</td>
						</tr>
						<tr>
							<td>Date of issue:</td>
							s
							<td>
								<input type="text" name="dateOfIssue" value="{good:getDateOfIssue($good)}" />
								<xsl:value-of select="errors:getValue($errors, 'dateOfIssue')" />
							</td>
						</tr>
						<tr>
							<td>Color:</td>
							<td>
								<input type="text" name="color" value="{good:getColor($good)}" />
								<xsl:value-of select="errors:getValue($errors, 'color')" />
							</td>
						</tr>
						<tr>
							<td>Price</td>
							<td>
								<input type="text" name="price" value="{good:getPrice($good)}" />
								<xsl:value-of select="errors:getValue($errors, 'price')" />
							</td>
						</tr>
						<tr>
							<td>Not in stock</td>
							<td>
								<xsl:choose>
									<xsl:when test="good:isNotInStock($good)">
										<input type="checkbox" name="notInStock" value="true"
											checked="checked" />
									</xsl:when>
									<xsl:otherwise>
										<input type="checkbox" name="notInStock" value="true" />
									</xsl:otherwise>
								</xsl:choose>
							</td>
							<xsl:value-of select="errors:getValue($errors, 'notInStock')" />
						</tr>
					</table>
					<input type="submit" value="SAVE_GOOD" />
				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>