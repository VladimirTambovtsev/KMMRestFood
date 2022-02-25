//
// Created by Владимир Тамбовцев on 25.02.2022.
// Copyright (c) 2022 orgName. All rights reserved.
//


import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeView: View {

    private let recipe: Recipe
    private let dateUtil: DatetimeUtil

    init(
            recipe: Recipe,
            dateUtil: DatetimeUtil
    ) {
        self.recipe = recipe
        self.dateUtil = dateUtil
    }

    var body: some View {
        ScrollView {
            VStack(alignment: .leading){
                WebImage(url: URL(string: recipe.featuredImage))
                        .resizable()
                        .placeholder(Image(systemName: "photo")) // Placeholder Image
                        .placeholder {
                            Rectangle().foregroundColor(.white)
                        }
                        .indicator(.activity)
                        .transition(.fade(duration: 0.5))
                        .scaledToFill() // 1
                        .frame(height: 250, alignment: .center) // 2
                        .clipped() // 3

                VStack(alignment: .leading){

                    HStack(alignment: .lastTextBaseline){
                        DefaultText(
                                "Updated \(dateUtil.humanizeDatetime(date: recipe.dateUpdated)) by \(recipe.publisher)"
                        )
                                .foregroundColor(Color.gray)

                        Spacer()

                        DefaultText(String(recipe.rating))
                                .frame(alignment: .trailing)
                    }

                    ForEach(recipe.ingredientsList as Array<String>, id: \.self){ ingredient in
                        DefaultText(ingredient)
                                .padding(.top, 4)
                    }
                }
                        .background(Color.white)
                        .padding(12)
            }
        }
    }
}