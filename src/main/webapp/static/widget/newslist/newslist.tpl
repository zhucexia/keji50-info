<% for(var i = 0,len = data.data.length; i<len ;i++){%>
<% var info = data.data[i],path = data.contextpath ; %>
    <article data="<%=info.id %>">
        <a class="pic info_flow_news_image badge-o2o before-fade-in after-fade-in" data-fit-mobile="true" href="<%=path %>/p/<%=info.id %>" style="background-image: url(<%=info.image %>);">
            <span class="mask-tags" data-type="<%=info.infoCategory.code %>" style="background-color: <%=info.infoCategory.colorCode %>"><%=info.infoCategory.name %></span>
        </a>
        <div class="desc">
            <a class="title info_flow_news_title" href="<%=path %>/p/<%=info.id %>" target="_blank"><%=info.title %></a>
            <div class="author">
            <a href="<%=path %>/a/<%=info.author.id %>">
                <span class="avatar before-fade-in" style="background-image: url(<%=info.author.image %>);"></span>
                <span class="name"><%=info.author.nickname %></span></a>
                <span class="time">&nbsp;•&nbsp;<time class="timeago" title="<%=info.createDate %>" datetime="<%=info.createDate %>"><%=info.createDate %></time></span>
            </div>
            <div class="brief"><%=info.shortDesc %></div>
        </div>
    </article>
<%}%>    