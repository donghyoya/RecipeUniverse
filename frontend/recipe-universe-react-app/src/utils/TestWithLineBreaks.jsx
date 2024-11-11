import React from 'react';

const TextWithLineBreaks = ({ text }) => (
  <>
    {text.split('\n').map((line, i, arr) => (
      <React.Fragment key={i}>
        {line}
        {i < arr.length - 1 && <br />}
      </React.Fragment>
    ))}
  </>
);

export default TextWithLineBreaks;