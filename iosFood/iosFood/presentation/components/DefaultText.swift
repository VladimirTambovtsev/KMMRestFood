//
// Created by Владимир Тамбовцев on 25.02.2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI

struct DefaultText: View {

    let text: String
    let size: CGFloat

    init(_ text: String, size: CGFloat = 15) {
        self.text = text
        self.size = size
    }

    var body: some View {
        Text(text)
                .font(Font.custom("Avenir", size: size))
    }
}

struct DefaultTextView_Previews: PreviewProvider {
    static var previews: some View {
        DefaultText("Just some text", size: 15)
    }
}