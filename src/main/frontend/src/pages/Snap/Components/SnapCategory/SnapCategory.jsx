import React, {useEffect, useState} from 'react';
import Filter from "widgets/Filter/Filter";
import { snapCategoryData } from "data/category";

const SnapCategory = ({ selectedCategories, setSelectedCategories }) => {

    const categoryProps = snapCategoryData.reduce((acc, cur) => {
        acc[cur.label] = cur.subCategories;
        return acc;
    }, {});


    return (
        <div className="snap_category">
            <Filter
                category_list={categoryProps}
                selectedCategories={selectedCategories}
                setSelectedCategories={setSelectedCategories}
            />
        </div>
    );
};

export default SnapCategory;