package com.hongluomeng.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Category;
import com.hongluomeng.model.CategoryAttribute;
import com.hongluomeng.model.Product;
import com.hongluomeng.service.ProductService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.ProductValidator;

public class ProductController extends BaseController {

	private ProductService productService = new ProductService();

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> resultMap = productService.list(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", resultMap));
    }

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Product product = productService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", product));
    }

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_CATEGORY_LIST)
	public void listCategory() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> resultMap = productService.listCategory(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", resultMap));
    }

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_CATEGORY_FIND)
	public void findCategory() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Category category = productService.findCategory(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", category));
    }

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_CATEGORY_SAVE)
	public void saveCategory() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.saveCategory(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUC_CATEGORYT_UPDATE)
	public void updateCategory() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.updateCategory(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUC_CATEGORYT_DELETE)
	public void deleteCategory() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.deleteCategory(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_CATEGORY_ATTRIBUTE_LIST)
	public void listCategoryAttribute() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<CategoryAttribute> categoryAttributeList = productService.listCategoryAttribute(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", categoryAttributeList));
    }

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_CATEGORY_ATTRIBUTE_FIND)
	public void findCategoryAttribute() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> resultMap = productService.findCategoryAttribute(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", resultMap));
    }

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUCT_CATEGORY_ATTRIBUTE_SAVE)
	public void saveCategoryAttribute() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.saveCategoryAttribute(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUC_CATEGORYT_ATTRIBUTE_UPDATE)
	public void updateCategoryAttribute() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.updateCategoryAttribute(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(ProductValidator.class)
	@ActionKey(Const.URL_PRODUC_CATEGORYT_ATTRIBUTE_DELETE)
	public void deleteCategoryAttribute() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		productService.deleteCategoryAttribute(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

}
