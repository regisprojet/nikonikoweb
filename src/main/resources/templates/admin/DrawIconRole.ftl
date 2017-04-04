
 <#if "role.role" == "ROLE_ADMIN">
<img class="roleDaD" id="iconAdmin" src="/images/admin_40.png">
 <#elseif "role.role" == "ROLE_USER">
 <img class="roleDaD" id="iconUser" src="/images/user_40.png">
 <#elseif "role.role" == "ROLE_VIP">
 <img class="roleDaD" id="iconVip" src="/images/vip_40.png">
 <#elseif "role.role" == "ROLE_PROJECT_LEADER">
  <img class="roleDaD" id="iconProjectLeader" src="/images/project_leader_40.png">
 </#if>