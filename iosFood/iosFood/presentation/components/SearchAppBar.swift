//
//  SearchAppBar.swift
//  iosFood
//
//  Created by Владимир Тамбовцев on 25.02.2022.
//  Copyright © 2022 orgName. All rights reserved.
//


import SwiftUI
import shared

struct SearchAppBar: View {
    
    @State var query: String
    private let foodCategories: [FoodCategory]
    private let onTriggerEvent: (RecipeListEvents) -> Void
    
    init(
        query: String,
        foodCategories: [FoodCategory],
        onTriggerEvent: @escaping (RecipeListEvents) -> Void
    ) {
        self.foodCategories = foodCategories
        self.onTriggerEvent = onTriggerEvent
        self._query = State(initialValue: query) // set initial value for query
    }
    
    var body: some View {
            VStack{
                HStack{
                    Image(systemName: "magnifyingglass")
                    TextField(
                        "Search...",
                        text: $query,
                        onCommit:{
                            onTriggerEvent(RecipeListEvents.NewSearch())
                        }
                    )
                    .onChange(of: query, perform: { value in
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(query: value))
                    })
                }
                .padding(.bottom, 8)
                ScrollView(.horizontal){
                    HStack(spacing: 10){
                        ForEach(foodCategories, id: \.self){ category in
                            FoodCategoryChip(
                                category: category.value,
                                isSelected: false // TODO
                            )
                            .onTapGesture {
                                query = category.value
                                // TODO
                            }
                        }
                    }
                }
            }
            .padding(.top, 8)
            .padding(.leading, 8)
            .padding(.trailing, 8)
            .background(Color.white.opacity(0))
        }
}