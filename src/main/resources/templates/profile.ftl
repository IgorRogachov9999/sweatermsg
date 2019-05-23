<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h5>${username}</h5>
    ${message?ifExists}
    <#if username == name>
        <a href="/edit">Edit</a>
    <#else>
        <a href="#">Send</a>
    </#if>
</@c.page>