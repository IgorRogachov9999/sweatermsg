<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h5>${username}</h5>
    <#if username == name>
        <a href="/edit">Edit</a>
    <#else>
        <a href="/chat/${name}/${username}">Send</a>
    </#if>
    ${message?ifExists}
</@c.page>