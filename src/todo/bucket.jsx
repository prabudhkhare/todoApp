import React from "react";

function Bucket(props) {
  const bucketItems = props.buckets;
  const bucketItemsMap = bucketItems.map((bucket) =>
    bucket.bucketId === 0 ? (
      ""
    ) : (
      <div
        onClick={() => props.setCurrentBucketId(bucket.bucketId)}
        key={bucket.bucketId}
        className="bucket-buttons"
      >
        <div
          className={
            props.selectedBucketId === bucket.bucketId
              ? "bucket-item active"
              : "bucket-item"
          }
        >
          <p>{bucket.name}</p>
        </div>
      </div>
    )
  );

  return <React.Fragment>{bucketItemsMap}</React.Fragment>;
}

export default Bucket;
