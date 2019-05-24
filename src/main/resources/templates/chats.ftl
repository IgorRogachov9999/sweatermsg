<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="list-group">
        <#list chats as chat>
            <a href="/chat/${name}/${chat.username}" class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">${chat.username}</h5>
                    <small class="text-muted">${chat.message.timestamp?number_to_datetime}</small>
                </div>
                <p class="mb-1">${chat.message.text}</p>
                <#if !chat.message.read>
                    <#if chat.receiver == name>
                        <small class="text-muted">You have unread messages</small>
                    </#if>
                </#if>
            </a>
        <#else>
            <h5>No chats</h5>
        </#list>
    </div>
</@c.page>
