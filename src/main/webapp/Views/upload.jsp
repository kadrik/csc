<%@include file="snippets/header.jsp"%>

<form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <br />
        <input type="submit" value="Upload" />
    </form>

<%@include file="snippets/footer.jsp"%>