package work.aijiu.common.utils;


import work.aijiu.entity.Menu;
import work.aijiu.entity.Organize;
import work.aijiu.entity.Permission;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuUtils {
    public static List<Permission> buildTree(List<Permission> permissionList) {
        Map<Integer, List<Permission>> zoneByParentIdMap = permissionList.stream().collect(Collectors.groupingBy(Permission::getParentId));
        permissionList.forEach(permission->permission.subs = zoneByParentIdMap.get(permission.id));
        return permissionList.stream().filter(v -> v.parentId==0).collect(Collectors.toList());
    }
    public static List<Permission> buildTree2(List<Permission> permissionList) {
        Map<Integer, List<Permission>> zoneByParentIdMap = permissionList.stream().collect(Collectors.groupingBy(Permission::getParentId));
        permissionList.forEach(permission->permission.children = zoneByParentIdMap.get(permission.id));
        return permissionList.stream().filter(v -> v.parentId==0).collect(Collectors.toList());
    }
    public static List<Organize> buildTree3(List<Organize> organizeList,String id) {
        Map<String, List<Organize>> zoneByParentIdMap = organizeList.stream().collect(Collectors.groupingBy(Organize::getOrganizeParent));
        organizeList.forEach(organize->organize.children = zoneByParentIdMap.get(organize.organizeId));
        return organizeList.stream().filter(v -> v.organizeParent.equals(id)).collect(Collectors.toList());
    }
}


