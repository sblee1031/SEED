package project.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import project.common.TreeMaker;
import project.common.UtilEtc;
import project.common.EtcSvc;

@Controller
public class DeptCtr {

    @Autowired
    private DeptSvc deptSvc;
    
    @Autowired
    private EtcSvc etcSvc;
    
    /**
     * 리스트.
     */
    @RequestMapping(value = "/adDepartment")
       public String department(HttpServletRequest request, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("PK_SAWON_CODE").toString();
        
        modelMap.addAttribute("PK_SAWON_CODE", userno);
        
        // etcSvc.setCommonAttribute(userno, modelMap);
        
        List<?> listview   = deptSvc.selectDepartment();

        TreeMaker tm = new TreeMaker();
        String treeStr = tm.makeTreeByHierarchy(listview);
        
        modelMap.addAttribute("treeStr", treeStr);
        
        return "system/p0001/Sawon_Enroll_popupDept";
    }

    /**
     * 부서 정보(하나).
     */ 
    @RequestMapping(value = "/adDepartmentRead")
       public void departmentRead(HttpServletRequest request, HttpServletResponse response) {
        
        String deptno = request.getParameter("deptno");

        DepartmentVO deptInfo = deptSvc.selectDepartmentOne(deptno);
        
        UtilEtc.responseJsonValue(response, deptInfo);
    }
    
}
