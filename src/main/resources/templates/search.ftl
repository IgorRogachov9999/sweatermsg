<#import "parts/common.ftl" as c>

<@c.page>
    <form action="/search" method="get">
        <div class="form-group row">
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Username" />
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Search</button>
    </form>
    <#list users as user>
        <div><br><a href="/user/${user.username}">${user.username}</a></div>
    <#else>
        User is not found!
    </#list>
</@c.page>