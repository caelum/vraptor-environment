<html>
<style type="text/css">
tbody tr:nth-child(even) {
	background-color: #B0C4DE;
}
tbody tr:nth-child(odd) {
	background-color: #F0F8FF;
}
table {
	border: 1px solid #CCC;
}
</style>

<br/>
<table>
	<thead>
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
		<#list environment.keys as key>
			<tr>
				<td>${key}</td>
				<td>
					<form method="post">
						<input type="hidden" name="_method" value="put"/>
						<input type="hidden" name="key" value="${key}" />
						<#assign value=environment.get(key)>
						<#if value == "true" || value == "false">
							<#if value == "true">
								<#assign trueSelected = "selected">
								<#assign falseSelected = "">
							<#else>
								<#assign trueSelected = "">
								<#assign falseSelected = "selected">
							</#if>
							<select name="value">
								<option value="true" ${trueSelected}>true</option>
								<option value="false" ${falseSelected}>false</option>
							</select>
						<#else>
							<input type="text" name="value" value="${value}" />
						</#if>
						<input type="submit" value="modify" />
					</form>
				</td>
			</tr>
		</#list>
	</tbody>
</table>
</html>