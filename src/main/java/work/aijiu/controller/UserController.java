package work.aijiu.controller;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.aop.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import work.aijiu.common.utils.TablePage;
import work.aijiu.entity.User;
import work.aijiu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-09-18 09:26:33
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }


    /**
     * 添加用户
     * @return
     */
    @PostMapping("addUser")
    public User addUser(){
        User user = new User();
        user.setUsername("你个大傻吊");
        return user;
    }

    @GetMapping("userpage")
    public TablePage uerPage(@RequestParam("currentPage")Integer currentPage, @RequestParam("pageSize")Integer pageSize){
        return userService.userPage(currentPage,pageSize);
    }

    @GetMapping("change")
    public boolean chage() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, NotFoundException, IOException, CannotCompileException, ClassNotFoundException {
        Method method = UserController.class.getMethod("addUser");
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions == null)
            throw new RuntimeException("please add testA");
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(requiresPermissions);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        String[] val = (String[])memberValues.get("value");
        for (int i = 0; i < val.length; i++) {
            System.out.println(val[0]);
        }
        val[0] = "user:add2";
        memberValues.put("value", val);
        System.out.println("改变后：" + requiresPermissions.value());

//        ClassPool aDefault = ClassPool.getDefault();
//        aDefault.insertClassPath(new ClassClassPath(UserController.class));
//        //使用类的全类名
//        CtClass ctClass = aDefault.get(UserController.class.getName());
//        //输入方法名获取方法对象
//        CtMethod ctMethod = ctClass.getDeclaredMethod("addUser");
//        //获取注解
//        Object[] annotations = ctMethod.getAnnotations();
//
//        RequiresPermissions requiresPermissions = (RequiresPermissions) annotations[0];
//        String val[] = requiresPermissions.value();
//        System.out.println(val[0]);



//        ClassPool pool = ClassPool.getDefault();
//        // 获取需要修改的类
//        CtClass ct = pool.get(UserController.class.getName());
//        // 获取类里的所有方法
//        CtMethod[] cms = ct.getDeclaredMethods();
//        for (CtMethod cm : cms) {
//            System.out.println("方法名称====" + cm.getName());
//
//            MethodInfo methodInfo = cm.getMethodInfo();
//
//            AnnotationsAttribute attribute = (AnnotationsAttribute) methodInfo
//                    .getAttribute(AnnotationsAttribute.visibleTag);
//            System.out.println(attribute);
//
//            ConstPool cPool = methodInfo.getConstPool();
//
//            AnnotationsAttribute attribute2 = new AnnotationsAttribute(cPool, AnnotationsAttribute.visibleTag);
//            Annotation[] anns= attribute2.getAnnotations();
//            for(Annotation ann:anns){
//                System.out.println(ann.getTypeName());
//            }
//            Annotation annotation = new Annotation("org.testng.annotations.Test", cPool);
//            annotation.addMemberValue("invocationCount", new LongMemberValue(10L, cPool));
//            attribute2.setAnnotation(annotation);
//            methodInfo.addAttribute(attribute2);
//
//            Annotation annotation2 = attribute2.getAnnotation("org.testng.annotations.Test");
//            long text = ((LongMemberValue) annotation2.getMemberValue("invocationCount")).getValue();
//            attribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
//
//            System.out.println(attribute);
//            System.out.println(text);
//        }

//        //获取当前i项目系统路径
//        File file = new File(".");
//        //找到class文件地址
//        String canonicalPath = file.getCanonicalPath() + "\\target\\classes\\work\\aijiu\\controller\\UserController.class";
//        byte[] bytes = ctClass.toBytecode();
//        //写入到文件夹 --->这样就可以修改编译之后的class文件了
//        FileOutputStream fileOutputStream = new FileOutputStream(canonicalPath);
//        fileOutputStream.write(bytes);
//        fileOutputStream.close();
        return true;
    }

}