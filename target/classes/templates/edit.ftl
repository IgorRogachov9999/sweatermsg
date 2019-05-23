<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group row">
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="New Password" />
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6">
                <input type="password" name="password2" class="form-control" placeholder="Repeat New Password" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>