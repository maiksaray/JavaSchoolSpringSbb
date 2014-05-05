<div id=content>
    <h1>${trainId}</h1>

    <div class="datagrid">
        <table>
            <thead>
            <tr>
                <th>station</th>
                <th>time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${schedule}" var="scheduleItem">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/stations/schedule/${scheduleItem.key}">${scheduleItem.key}</a>
                    </td>
                    <td>${scheduleItem.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>