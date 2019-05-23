<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Username" />
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <div class="col-sm-6">
                <input type="password" name="password2" class="form-control" placeholder="Repeat Password" />
            </div>
        </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Sign up<#else>Sign In</#if></button>
    <#if !isRegisterForm><br><a href="/registration">Registration</a></#if>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Sign Out</button>
</form>
</#macro>
