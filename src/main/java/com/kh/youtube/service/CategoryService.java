package com.kh.youtube.service;

import com.kh.youtube.domain.Category;
import com.kh.youtube.repository.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO dao;

    public List<Category> showAll(){
        return dao.findAll();   // spring이 DAO에 findAll 메소드가 없어도 자동으로 만들어 준다.
    }

    public Category show(int categoryCode) {
        return dao.findById(categoryCode).orElse(null);   //findById는 반환 값이 Optional이기 때문에 orElse를 명시 해주어야 한다.
    }

    public Category create(Category category){
        return dao.save(category);
    }

    public Category update(Category category){
        return dao.save(category);  // 추가, 수정은 모두 save 메소드이다.(primary key로 DB상에 없으면 추가, 있으면 수정)
    }

    public Category delete(int categoryCode){

        // delete 메소드는 인자값이 Entity이므로 Category 객체를 가지고 온다.(위에 show메소드로 가져와도 됨)
        Category data = dao.findById(categoryCode).orElse(null);

        dao.delete(data);
        return data;

    }

}
