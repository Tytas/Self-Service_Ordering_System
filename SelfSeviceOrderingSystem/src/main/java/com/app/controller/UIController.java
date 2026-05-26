package com.app.controller;

import com.app.model.menu.MenuCatalog;
import com.app.model.menu.MenuCategory;
import com.app.model.product.Product;
import com.app.view.CategoryPageView;
import com.app.view.MenuView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIController {
	private final BorderPane root;

	public UIController(BorderPane root) {
		this.root = root;
	}

	public void showMenu() {
		MenuView menuView = new MenuView();
		root.setCenter(menuView.build(MenuCatalog.getCategories(), this::showCategory));
	}

	public void showCategory(MenuCategory category) {
		CategoryPageView categoryPageView = new CategoryPageView();
		root.setCenter(categoryPageView.build(category, MenuCatalog.getProducts(category), this::showMenu, product -> showProductDetails(category, product)));
	}

	private void showProductDetails(MenuCategory category, Product product) {
		Stage owner = root.getScene() != null && root.getScene().getWindow() instanceof Stage stage ? stage : null;
		CategoryPageView.showProductDialog(owner, category, product);
	}
}
