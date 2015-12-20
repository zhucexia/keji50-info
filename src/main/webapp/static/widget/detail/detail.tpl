<% for(var i = 0,len = data.data.length; i<len ;i++){%>
<% var comment = data.data[i],path = data.contextpath ; %>
<div class="comment cf comment_details" data-comment-id="<%=comment.id %>">
    <div class="avatar left">
        <a href="javascript:void(0)"><img alt="科技50用户<%=comment.author.nickname %>" data-lazyload="<%=comment.author.image %>" raw_iden="<%=comment.id %>" class="before-fade-in"></a>
    </div>
    <div class="comment-wrapper">
        <div class="postmeta"><a class="user_info_name" href="javascript:void(0)"><%=comment.author.nickname %></a>&nbsp;•&nbsp;
            <abbr class="timeago" title="<%=comment.createDate %>"><%=comment.createDate %></abbr>
        </div>
        <div class="commemt-main">
            <p><%=comment.content %></p>
        </div>
        <div class="opts"></div>
        <a class="pull-right" href="javascript:;"> 回复</a></div>
</div>

<%}%>   