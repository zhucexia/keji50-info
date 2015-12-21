<% for(var i = 0,len = data.data.length; i<len ;i++){%>
<% var comment = data.data[i],path = data.contextpath ; %>
<div class="comment cf comment_details" data-comment-id="<%=comment.id %>">
    <div class="avatar left">
        <a href="javascript:void(0)"><img alt="科技50用户<%=comment.author.nickname %>" src="<%=comment.author.image %>" raw_iden="<%=comment.id %>" class="before-fade-in"></a>
    </div>
    <div class="comment-wrapper">
        <div class="postmeta"><a class="user_info_name" href="javascript:void(0)"><%=comment.author.nickname %></a>&nbsp;•&nbsp;
            <abbr class="timeago" title="<%=comment.createDate %>"><%=comment.createDate %></abbr>
        </div>
        <div class="commemt-main">
        	<p <% if(comment.state != 'c') { %>class="pending"<% } %>>
        	<%  if(comment.state == 'c') { %>
        	<span title="该评论正在审核中, 仅对本人可见" class="badge badge-warning">审核中</span>
        	<% } 
        		if (comment.toAuthor) {
        	%>
        	<span class="replay-tips">回复<%=comment.toAuthor %>：</span>
        	<% } %>
            <%=comment.content %>
            </p>
        </div>
        <div class="opts"></div>
        <a class="pull-right" data-author="<%=comment.author.nickname%>" href="javascript:;"> 回复</a></div>
</div>
<%}%>   