<#import "parts/common.ftl" as c>

<@c.page>
        <div class="form-group mt-3">
            <form method="post">
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           name="text" placeholder="Message" value="<#if message??>${message.text}</#if>"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Send</button>
                </div>
            </form>
        </div>

    <div class="list-group">
        <#list chat as message>
            <div class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    <#list users as user>
                        <#if user.id == message.senderId>
                            <h5 class="mb-1"><a href="/user/${user.username}">${user.username}</a></h5>
                            <#break>
                        </#if>
                    </#list>
                    <small class="text-muted">${message.timestamp?number_to_datetime}</small>
                </div>
                <p class="mb-1">${message.text}</p>
            </div>
        </#list>
    </div>
</@c.page>
