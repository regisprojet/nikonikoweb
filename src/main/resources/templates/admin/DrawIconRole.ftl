
 <#if "${role.role}" == "ROLE_ADMIN">
<img class="roleDaD" id="iconAdmin" value="iconAdmin" src="/images/admin_40.png">
 <#elseif "${role.role}" == "ROLE_USER">
 <img class="roleDaD" id="iconUser" value="iconUser"  src="/images/user_40.png">
 <#elseif "${role.role}"== "ROLE_VIP">
 <img class="roleDaD" id="iconVip" value="iconVip" src="/images/vip_40.png">
 <#elseif "${role.role}" == "ROLE_PROJECTLEADER">
  <img class="roleDaD" id="iconProjectLeader" value="iconProjectLeader" src="/images/project_leader_40.png">
 </#if>