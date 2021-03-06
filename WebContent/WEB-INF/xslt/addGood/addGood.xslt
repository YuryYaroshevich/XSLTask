<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<xsl:template match="/">
	    <xsl:param name="categoryName" />
		<xsl:param name="subcategoryName" />
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
					<input type="hidden" name="category" value="{$categoryName}" />
					<input type="hidden" name="subcategory" value="{$subcategoryName}" />
					<table>
						<tr>
							<td>Producer:</td>
							<td>
								<input type="text" name="producer" />
							</td>
						</tr>
						<tr>
							<td>Model:</td>
							<td>
								<input type="text" name="model" />
							</td>
						</tr>
						<tr>
							<td>Date of issue:</td>
							<td>
								<input type="text" name="dateOfIssue" />
							</td>
						</tr>
						<tr>
							<td>Color:</td>
							<td>
								<input type="text" name="color" />
							</td>
						</tr>
						<tr>
							<td>Price</td>
							<td>
								<input type="text" name="price" />
							</td>
						</tr>
						<tr>
							<td>Not in stock</td>
							<td>
								<input type="checkbox" name="notInStock" />
							</td>
						</tr>
					</table>
					<input type="submit" value="SAVE GOOD" />
				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>