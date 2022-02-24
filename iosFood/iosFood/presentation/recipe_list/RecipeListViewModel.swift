//
//  RecipeListViewModel.swift
//  iosFood
//
//  Created by Владимир Тамбовцев on 24.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil
    
    @Published var state: RecipeListState = RecipeListState()
    
    init(searchRecipes: SearchRecipes, foodCategoryUtil: FoodCategoryUtil) {
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
    }
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ) {
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentState.recipes,
            queue: queue ?? currentState.queue
        )
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents) {
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            print("nothing")
        case is RecipeListEvents.NextPage:
            print("nothing")
        case is RecipeListEvents.OnUpdateQuery:
            print("nothing")
        case is RecipeListEvents.OnSelectCategory:
            print("nothing")
        case RecipeListEvents.OnRemoveHeadMessageFromQueue():
            print("nothing")
        default:
            print("nothing")
        }
    }
    
    func loadRecipes() {
        let currentState = self.state.copy() as! RecipeListState
        do {
            try searchRecipes.execute(page: Int32(currentState.page), query: currentState.query).collectCommon(coroutineScope: nil) {
                (dataState: DataState<NSArray>?) in
                if (dataState != nil) {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false
                    self.updateState(isLoading: loading)
                    
                    if (data != nil) {
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                    if (message != nil) {
                        self.handleMessageByUIComponentType(message!.build())
                    }
                }
            }
        } catch {
            print("Error loadRecipes(): \(error)")
        }
    }
    
    private func appendRecipes(recipes: [Recipe]){
        for recipe in recipes {
            print("Recipe: \(recipe.title)")
        }
        // TODO("append recipes to state")
    }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        // TODO("append to queue or 'None'")
    }
}
